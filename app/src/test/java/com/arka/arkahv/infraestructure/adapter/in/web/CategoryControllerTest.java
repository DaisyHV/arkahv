package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.port.in.CategoryUseCase;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.CategoryDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.CategoryWebMapper;
import com.arka.arkahv.infraestructure.security.JwtFiltroPeticion;
import com.arka.arkahv.infraestructure.security.ServicioDetallesUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class CategoryControllerTest {


    }

