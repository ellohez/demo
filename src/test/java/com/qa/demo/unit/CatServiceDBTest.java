package com.qa.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.demo.domain.Cat;
import com.qa.demo.repo.CatRepo;
import com.qa.demo.service.CatService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {
    
    @Autowired
    private CatService service;

    @MockBean
    private CatRepo repo;

    @Test
    void testUpdate() {

        // This relies on the Cat.Equals() method to compare the mocked cat to the actual cat
        int id = 1;
        Cat existingCat = new Cat((long)id, "Cindy Clawford", false, true, 10);
        Cat updatedCat = new Cat((long)id, "Jason", true, true, 10);

        // Mock the repo methods that will be called as we are testing 
        //  CatServiceDB not the CatRepo
        Mockito.when(this.repo.findById((long)id)).thenReturn(Optional.of(existingCat));
        Mockito.when(this.repo.save(updatedCat)).thenReturn(updatedCat);
        
        assertEquals(updatedCat, this.service.updateCat(id, updatedCat.getName(), 
                        updatedCat.isHasWhiskers(),
                        updatedCat.isEvil(),
                        updatedCat.getLength()));
    }
}
