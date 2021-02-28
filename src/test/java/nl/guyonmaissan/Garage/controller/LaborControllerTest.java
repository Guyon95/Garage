package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.dbmodel.Labor;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.service.LaborService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LaborControllerTest {

    @Mock
    LaborService laborService;

    @InjectMocks
    LaborController controller;

    Labor labor;

    ReturnObject returnObject;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        labor = new Labor();
        labor.setId(1L);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }

    @Test
    @WithMockUser
    void createPart() throws Exception {
        //when(laborService.createLabor(labor)).thenReturn(returnObject);

        returnObject.setObject(labor);
        returnObject.setMessage("Check");

        mockMvc.perform(post("/labor/create"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("returnObject",returnObject));
    }
}