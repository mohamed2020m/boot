package app.leeuw.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import app.leeuw.demo.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findById(int id);
    @Query("select year(s.dateNaissance) as annee, count(*) as nbr from Student s group by year(s.dateNaissance) order by year(s.dateNaissance)")
    Collection<?> findNbrStudentByYear();

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("update Student s set s.nom = ?1, s.prenom = ?2, s.dateNaissance = ?3 WHERE  s.id = ?4")
    void updateStudent(String nom, String prenom, Date dateNaissance, Integer  id);
}
