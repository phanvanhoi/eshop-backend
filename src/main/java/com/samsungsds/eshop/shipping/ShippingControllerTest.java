// package com.samsungsds.eshop.shipping;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.restdocs.RestDocumentationContextProvider;
// import org.springframework.restdocs.RestDocumentationExtension;
// import org.springframework.restdocs.payload.JsonFieldType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import com.google.gson.Gson;
// import com.samsungsds.eshop.payment.Money;

// import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
// import static org.mockito.BDDMockito.*;
// import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
// import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
// import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

// import java.util.List;

// @ExtendWith({RestDocumentationExtension.class})
// @WebMvcTest(ShippingController.class)
// public class ShippingControllerTest {
//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private ShippingService shippingService;    

//     @BeforeEach
//     void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//         this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                 .apply(documentationConfiguration(restDocumentation)) 
//                 .build();
//     }

//     @Test
//     void testCalculateShippingCost() throws Exception {
//         ShippingItem shippingItem1 = new ShippingItem();
//         shippingItem1.setProductId("id1");
//         shippingItem1.setQuantity(2);

//         ShippingItem shippingItem2 = new ShippingItem();
//         shippingItem2.setProductId("id2");
//         shippingItem2.setQuantity(1);
//         List<ShippingItem> shippingList = List.of(shippingItem1, shippingItem2);

//         Money shippingCost = new Money();
//         shippingCost.setCurrencyCode("USD");
//         shippingCost.setUnits(10);
//         shippingCost.setNanos(2);
//         given(shippingService.calculateShippingCostFromCount(3)).willReturn(shippingCost);

//         Gson gson = new Gson();
//         String inputString = gson.toJson(shippingList);

//         this.mockMvc.perform(
//             MockMvcRequestBuilders
//                 .post("/api/checkouts/shippings/cost")
//                 .content(inputString)
//                 .contentType(MediaType.APPLICATION_JSON)
//         )
//         .andExpect(status().isOk())
//         .andDo(
//             document("post-shipping-cost",
//                 requestFields( 
//                     fieldWithPath("[].productId").type(JsonFieldType.STRING).description("배송 요청 상품 id"), 
//                     fieldWithPath("[].quantity").type(JsonFieldType.NUMBER).description("배송 요청 상품 갯수")
//                 ),
//                 responseFields(
//                     fieldWithPath("currencyCode").description("배송비 통화 코드"),
//                     fieldWithPath("units").description("배송비 Unit"),
//                     fieldWithPath("nanos").description("배송비 Nano")
//                 )
//             )
//         );
//     }
// }
