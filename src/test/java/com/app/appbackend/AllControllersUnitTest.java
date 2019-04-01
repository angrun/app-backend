package com.app.appbackend;

import com.app.appbackend.controllers.BrowseController;
import com.app.appbackend.dao.BrowseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BrowseController.class)
public class AllControllersUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BrowseDao browseDao;

    @Test
    public void getAllUsersSuccess() throws Exception {


        mvc.perform(get("/browse/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("Test")));
    }

}
