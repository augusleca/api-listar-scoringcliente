//package com.bcba.nosisensolnet.db;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data //Incluye getter y setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@RepositoryRestResource(exported = false) // Para que no genere endpoints sobre esto
//// @Table(name = "informes")??? los chicos ni la usan
//public class CuitEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String cuit;
//
//    @OneToMany(mappedBy = "cuit", cascade = CascadeType.ALL)
//    private List<InformeDB> informes = new ArrayList<>();
//
//}
