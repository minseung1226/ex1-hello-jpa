package hellojpa;

import hellojpa.cascade.Child;
import hellojpa.cascade.Parent;
import hellojpa.type.Address;
import hellojpa.type.AddressEntity;
import hellojpa.type.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Member member = new Member();
            member.setName("user1");
            member.setHomeAddress(new Address("homecity1","street1","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("city1","street2","20000"));
            member.getAddressHistory().add(new AddressEntity("city2","street3","30000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===== 조회 스타트 ====");
            Member findMember = em.find(Member.class, member.getId());
            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");


            findMember.getAddressHistory().remove(new AddressEntity("city1","street2","20000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1","street2","20000"));


            tx.commit();
        }catch (Exception e){

            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }

}
