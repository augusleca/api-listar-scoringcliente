//package com.bcba.nosisensolnet.db;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@RepositoryRestResource(exported = false) // Para que no genere endpoints sobre esto
//// @Table(name = "informes")???
//public class InformeDB {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cuit")
//    private CuitEntity cuitEntity;
//
//    @Column(name = "informe")
//    private String informeXML;
//
//    public void setCuit(String nuevoCuit){
//
//        cuitEntity.setCuit(nuevoCuit);
//    }
//}
//
///*
//public class InformeDB {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cuit")
//    private cuitEntity cuitEntity;
//
//    @Column(name = "informe")
//    private String informeXML;
//}
// */
