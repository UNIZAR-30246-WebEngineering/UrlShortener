package urlshortener.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urlshortener.demo.controller.impl.QrApiController;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class QrTests {

    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private QrApiController qrApiController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(qrApiController).build();
    }

    @Test
    public void uriGetWorks() throws Exception {
         mockMvc.perform(get("/qr/{id}/", "www.google.es"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.uri", is("www.google.es")))
                .andExpect(jsonPath("$.qr", is("iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC")));
    }
    
    @Test
    public void uriGetError() throws Exception {
         mockMvc.perform(get("/qr/{id}/", ""))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
