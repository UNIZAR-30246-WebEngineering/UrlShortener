package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.advice.BaseServiceAdvice;
import urlshortener.demo.controller.impl.UriApiController;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.service.URIService;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static urlshortener.demo.web.fixture.UriItemFixture.someURI;

public class UriTests {
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private URIService service;

    @InjectMocks
    private UriApiController uriApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(uriApiController)
                .setControllerAdvice(new BaseServiceAdvice()).build();
    }

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
