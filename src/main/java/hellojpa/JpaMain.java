package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setName("user");
            member.setCreateBy("kim");
            member.setCreateDate(LocalDateTime.now());
            em.persist(member);

            tx.commit();
        }catch (Exception e){
            System.out.println("예외발생");
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
