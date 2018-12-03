package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.impl.UriApiController;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.service.impl.URIServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UriTests {
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private URIServiceImpl service;

    @InjectMocks
    private UriApiController uriApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(uriApiController).build();
    }

    @Test
    public void getUriWorks() throws Exception {
        URIItem item = (URIItem) new URIItem().id("abc").redirection("https://google.es").hashpass("abc");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(new URI(item.getRedirection()));
        ResponseEntity<Void> redirection = new ResponseEntity<>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
        when(uriApiController.getURI(item.getId())).thenReturn(redirection);

        mockMvc.perform(get("/uri/{id}", item.getId())).andDo(print())
                .andExpect(status().isTemporaryRedirect())
                .andExpect(redirectedUrl(item.getRedirection()));
    }

    @Test
    public void getUriError() throws Exception {
        when(uriApiController.getURI("1")).thenThrow(new UnknownEntityException(1, "Unknown URI: 1"));
        mockMvc.perform(get("/uri/{id}", "1")).andDo(print())
                .andExpect(status().isTemporaryRedirect());
    }
}
