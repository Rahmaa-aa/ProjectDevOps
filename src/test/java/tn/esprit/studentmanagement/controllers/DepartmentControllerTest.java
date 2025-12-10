package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Collections;
import java.util.List;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartmentService departmentService;

    @Test
    void testGetAllDepartments() throws Exception {
        // 1. Prepare Mock Data
        Department department = new Department();
        department.setIdDepartment(1L);
        department.setName("IT");
        department.setLocation("Block A");
        
        List<Department> departmentList = Collections.singletonList(department);
        
        // 2. Define Behavior
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departmentList);

        // 3. Perform Request & Verify
        // Note: @WebMvcTest focuses on the Controller. The path here matches @RequestMapping in the controller.
        // It does not include the server.context-path (/student) which is a deployment property.
        mockMvc.perform(MockMvcRequestBuilders.get("/Depatment/getAllDepartment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("IT"));
                
        System.out.println("✅ Controller Test Passed: /Depatment/getAllDepartment returned 200 OK and correct JSON.");
    }
}
