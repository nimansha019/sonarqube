package com.gl.lms.controller;

import com.gl.lms.dto.ResponseDTO;
import com.gl.lms.dto.ReviewsDTO;
import com.gl.lms.dto.UsersDTO;
import com.gl.lms.exception.LibraryManagementSystemException;
import com.gl.lms.service.LibraryManagementSystemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.gl.lms.dto.AuthorsDTO;

import java.util.List;

@RestController
@RequestMapping("/LMS")
@CrossOrigin
@Validated
@RequiredArgsConstructor
public class LibraryManagementSystemController {

    private final LibraryManagementSystemService libraryManagementSystemService;

    @PostMapping("/add-user-and-issue-library-card")
    public ResponseEntity<ResponseDTO> addUserAndIssueLibraryCard(@RequestBody @Valid UsersDTO usersDTO) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.addUserAndIssueLibraryCard(usersDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping(value="/fetch-user-and-issued-library-card/{email}")
    public ResponseEntity<UsersDTO> fetchUserAndIssuedLibraryCardByEmail(@PathVariable @Email(message = "Email should be in valid format") String email) throws LibraryManagementSystemException{

        UsersDTO usersDTO = libraryManagementSystemService.fetchUserAndIssuedLibraryCardByEmail(email);

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @PutMapping(value = "update-name/{email}/{updatedName}")
    public ResponseEntity<ResponseDTO> updateName(@PathVariable   @Email(message = "Email should be in valid format") String email, @PathVariable   @NotBlank(message = "name should not be null") String updatedName) throws LibraryManagementSystemException{

        ResponseDTO responseDTO = libraryManagementSystemService.updateName(email, updatedName);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete-user/{email}")
    public ResponseEntity<ResponseDTO> deleteUserAndAssociatedLibraryCard(@PathVariable   @Email(message = "Email should be in valid format") String email) throws LibraryManagementSystemException{

        ResponseDTO responseDTO = libraryManagementSystemService.deleteUserAndAssociatedLibraryCard(email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "add-author-and-books")
    public ResponseEntity<ResponseDTO> addAuthorAndBooks(@RequestBody @Valid AuthorsDTO authorsDTO) throws LibraryManagementSystemException{

        ResponseDTO responseDTO = libraryManagementSystemService.addAuthorAndBooks(authorsDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-reviews/{title}")
    public ResponseEntity<ResponseDTO> addReviews(@PathVariable  @NotBlank(message = "Title should not be null or blank") String title,@RequestBody @Valid ReviewsDTO reviewsDTO) throws LibraryManagementSystemException {

        ResponseDTO responseDTO = libraryManagementSystemService.addReviews(title, reviewsDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value ="/fetchBookDetailsAndReviews/{title}" )
    public ResponseEntity<List<ReviewsDTO>> fetchBookDetailsAndReviews(@PathVariable  @NotBlank(message = "Title should not be null or blank") String title) throws LibraryManagementSystemException {

        List<ReviewsDTO> reviewsdto = libraryManagementSystemService.fetchBookDetailsAndReviews(title);
        return new ResponseEntity<>(reviewsdto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-book/{title}")
    public ResponseEntity<ResponseDTO> deleteBookAndAssociatedReviews(@PathVariable  @NotBlank(message = "Title should not be null or blank") String title) throws LibraryManagementSystemException {
        ResponseDTO responseDTO = libraryManagementSystemService.deleteBookAndAssociatedReviews(title);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    }
