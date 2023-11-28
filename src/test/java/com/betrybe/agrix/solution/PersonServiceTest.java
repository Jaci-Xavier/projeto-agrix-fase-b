package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonServiceTest {
  @Autowired
  PersonService personService;

  @MockBean
  PersonRepository personRepository;

  @Test
  public void getPersonByIdTest() {
    Person person = new Person();

    person.setId(1L);
    person.setUsername("xavier");
    person.setPassword("123");
    person.setRole(Role.USER);

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

    Person personFound = personService.getPersonById(1L);

    Mockito.verify(personRepository).findById(1L);

    Assertions.assertEquals(person.getId(), personFound.getId());
    Assertions.assertEquals(person.getUsername(), personFound.getUsername());
    Assertions.assertEquals(person.getPassword(), personFound.getPassword());
    Assertions.assertEquals(person.getRole(), personFound.getRole());
  }

  @Test
  public void getPersonByIdNotFoundTest() {
    Mockito.when(personRepository.findById(222L)).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> {
      personService.getPersonById(3331L);
    });
  }

  @Test
  public void getPersonByUsernameTest() {
    Person person = new Person();

    person.setId(1L);
    person.setUsername("xavier");
    person.setPassword("123");
    person.setRole(Role.USER);

    Mockito.when(personRepository.findByUsername("xavier")).thenReturn(Optional.of(person));

    Person personFound = personService.getPersonByUsername("xavier");

    Mockito.verify(personRepository).findByUsername("xavier");

    Assertions.assertEquals(person.getId(), personFound.getId());
    Assertions.assertEquals(person.getUsername(), personFound.getUsername());
    Assertions.assertEquals(person.getPassword(), personFound.getPassword());
    Assertions.assertEquals(person.getRole(), personFound.getRole());
  }

  @Test
  public void getPersonByUsernameNotFoundTest() {
    Mockito.when(personRepository.findByUsername("xavier")).thenReturn(Optional.empty());

    Assertions.assertThrows(PersonNotFoundException.class, () -> {
      personService.getPersonByUsername("xavier");
    });
  }

  @Test
  public void savePersonTest() {
    Person person = new Person();

    person.setId(1L);
    person.setUsername("xavier");
    person.setPassword("123");
    person.setRole(Role.USER);

    Person personToSave = new Person();

    personToSave.setId(person.getId());
    personToSave.setUsername(person.getUsername());
    personToSave.setPassword(person.getPassword());
    personToSave.setRole(person.getRole());

    Mockito.when(personRepository.save(person)).thenReturn(personToSave);

    Person personSaved = personService.create(person);

    Mockito.verify(personRepository).save(person);

    Assertions.assertEquals(person.getId(), personSaved.getId());
    Assertions.assertEquals(person.getUsername(), personSaved.getUsername());
    Assertions.assertEquals(person.getPassword(), personSaved.getPassword());
    Assertions.assertEquals(person.getRole(), personSaved.getRole());
  }
}
