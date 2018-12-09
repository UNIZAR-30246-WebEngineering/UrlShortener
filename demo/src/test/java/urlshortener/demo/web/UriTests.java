package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.advice.BaseServiceAdvice;
import urlshortener.demo.controller.impl.UriApiController;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.repository.URIRepository;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static urlshortener.demo.web.MockUtils.mapObject;
import static urlshortener.demo.web.fixture.UriItemFixture.*;

public class UriTests {
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private URIRepository service;

    @InjectMocks
    private UriApiController uriApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(uriApiController)
                .setControllerAdvice(new BaseServiceAdvice()).build();
    }

    /*
     * Test PUT /uri/{id}
     */
    @Test
    public void createWithNameOK() throws Exception{
        //Test 1: Create OK
        doNothing().when(service).add(isA(URIItem.class));

        URICreate uriCreate = someCorrectURICreate();
        String id = "randomID";
        mockMvc.perform(put("/uri/{id}", id).contentType(MediaType.APPLICATION_JSON).content(mapObject(uriCreate))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.redirection").value(uriCreate.getUri()));
    }

    @Test
    public void createWithNameInvalidURL() throws Exception {
        //Test 2: Create with invalid URL

        doNothing().when(service).add(isA(URIItem.class));

        URICreate uriCreate = someEmptyURICreate();
        String id = "randomID";
        mockMvc.perform(put("/uri/{id}", id).contentType(MediaType.APPLICATION_JSON).content(mapObject(uriCreate))).andDo(print())
                .andExpect(status().isBadRequest());

        uriCreate = someNullURICreate();
        mockMvc.perform(put("/uri/{id}", id).contentType(MediaType.APPLICATION_JSON).content(mapObject(uriCreate))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createWithNameInvalidHash() throws Exception{
        //Test 3: Create uri with and existing id.

        doThrow(CannotAddEntityException.class).when(service).add(isA(URIItem.class));

        URICreate uriCreate = someCorrectURICreate();
        String id = "randomID";
        mockMvc.perform(put("/uri/{id}/", id).contentType(MediaType.APPLICATION_JSON).content(mapObject(uriCreate))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    /*
     * Test PUT /uri
     */

    /*
     * Test DELETE /uri/{id}
     */

    /*
     * Test GET /uri/{id}
     */
    @Test
    public void getUriWorks() throws Exception {
        URIItem item = someURI();
        when(service.get("abc")).thenReturn(someURI());

        mockMvc.perform(get("/uri/{id}", item.getId())).andDo(print())
                .andExpect(status().isTemporaryRedirect())
                .andExpect(redirectedUrl(item.getRedirection()));
    }

    @Test
    public void getUriError() throws Exception {
        when(service.get("1")).thenReturn(null);
        mockMvc.perform(get("/uri/{id}", "1")).andDo(print())
                .andExpect(status().isNotFound());
    }


}
