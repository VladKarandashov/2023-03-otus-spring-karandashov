package ru.otus.hw12booksapp.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw12booksapp.service.BookService;
import ru.otus.hw12booksapp.view.AdminController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
public class SecurityTest {

    @MockBean
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testAuthenticatedOnAdmin() throws Exception {
        mockMvc.perform(get("/admin/library"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "client",
            authorities = {"ROLE_CLIENT"}
    )
    @Test
    public void testAuthenticatedOnClient() throws Exception {
        mockMvc.perform(get("/client/library"))
                .andExpect(status().isOk());
    }
}