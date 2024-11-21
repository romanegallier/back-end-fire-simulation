package com.example.forestfire.controler;
import com.example.forestfire.model.CellState;
import com.example.forestfire.model.ForestCell;
import com.example.forestfire.service.FirePropagationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ForestFireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirePropagationService firePropagationService;

    @Test
    public void testInitialize() throws Exception {
        CellState[][] mockForest = new CellState[10][10];
        when(firePropagationService.initializeForest()).thenReturn(mockForest);

        mockMvc.perform(get("/api/forest/initialize"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testNextStep() throws Exception {
        List<ForestCell> mockCells = List.of(new ForestCell(0, 0, CellState.BURNING));
        when(firePropagationService.simulateStep()).thenReturn(mockCells);

        mockMvc.perform(get("/api/forest/step"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].x").value(0))
                .andExpect(jsonPath("$[0].y").value(0))
                .andExpect(jsonPath("$[0].state").value("BURNING"));
    }
}