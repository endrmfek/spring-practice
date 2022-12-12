package hoteldelluna.springweb.boot.controller;

import com.google.gson.Gson;
import hoteldelluna.springweb.boot.dto.BootProductDto;
import hoteldelluna.springweb.boot.dto.BootProductResponseDto;
import hoteldelluna.springweb.boot.service.impl.BootProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@WebMvcTest(BootProductController.class)
class BootProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BootProductServiceImpl productService;


    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProduct() throws Exception {
        //static BDDMokito
        given(productService.getProduct(123L)).willReturn(
                new BootProductResponseDto(123L , "pen", 5000, 2000));

        String productId = "123";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/boot/product?number=" + productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProduct() throws Exception {
        //Mock객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 가정을 해줘야됨.
        BDDMockito.given(productService.saveProduct(new BootProductDto("pen" , 5000, 2000)))
                .willReturn(new BootProductResponseDto(12315L, "pen", 5000, 2000));

        BootProductDto productDto = new BootProductDto("pen", 5000, 2000);

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/boot/product")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").exists())
                .andDo(MockMvcResultHandlers.print());

        verify(productService).saveProduct(new BootProductDto("pen", 5000, 2000));
    }

    @Test
    void changeProductName() {
    }

    @Test
    void deleteProduct() {
    }
}