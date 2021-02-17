package com.spring.reactive.controller.rest;

import com.spring.reactive.model.read.CityReadDto;
import com.spring.reactive.model.read.GenericReactivePagedDto;
import com.spring.reactive.model.write.CityWriteDto;
import com.spring.reactive.service.mediator.ReactiveCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "cities", description = "Retrieve and manage cities")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/versions/1")
public class CityController{

    final ReactiveCityService reactiveCityService;

    @PageableAsQueryParam
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityReadDto.class)))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Get all Cities")
    @GetMapping(value = "/cities" )
    @ResponseStatus(HttpStatus.OK)
    public Flux<GenericReactivePagedDto<CityReadDto>> listCities(Pageable page) {
        return reactiveCityService.listCities(page);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",content = @Content(schema = @Schema(implementation = CityReadDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Create new City")
    @PostMapping(value = "/cities" )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CityReadDto> createCity(@Valid @RequestBody CityWriteDto cityWriteDto) {
        return reactiveCityService.createCity(cityWriteDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Update existing City")
    @PutMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CityReadDto> updateCity(@PathVariable String cityId, @Valid @RequestBody CityWriteDto cityWriteDto) {
        return reactiveCityService.updateCity(cityId,cityWriteDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Get existing City")
    @GetMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CityReadDto> getCity(@PathVariable String cityId) {
        return reactiveCityService.getCity(cityId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)

    })
    @Operation(summary = "Delete existing City")
    @DeleteMapping("/cities/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Boolean> deleteCity(@PathVariable String cityId) {
        return reactiveCityService.deleteCity(cityId);
    }
}
