package urlshortener.common.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.UriApiController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UriTests {
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UriApiController uriApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(uriApiController).build();
    }

    @Test
    public void getUriWorks() throws Exception {
        mockMvc.perform(get("/uri/{id}", "1")).andDo(print())
                .andExpect(status().isTemporaryRedirect())
                .andExpect(redirectedUrl("https://google.es"));
    }
}
