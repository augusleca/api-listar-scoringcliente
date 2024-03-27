//package com.bcba.nosisensolnet.repositories;
//
//import com.bcba.nosisensolnet.db.InformeDB;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//
//
//public interface InformeRepository extends JpaRepository<InformeDB, String> {
//    @Query(nativeQuery = true, value = "  ")
//    Optional<InformeDB> findByCuit(@Param("cuit") String cuit);
//}
