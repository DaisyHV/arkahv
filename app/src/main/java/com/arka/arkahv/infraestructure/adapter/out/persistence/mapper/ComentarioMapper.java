package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.Comentario;
import com.arka.arkahv.domain.model.User;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.ComentarioEntity;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    ComentarioEntity comentarioToComentarioEntity(Comentario comentario);

    Comentario comentarioEntityToComentario(ComentarioEntity comentarioEntity);
}
