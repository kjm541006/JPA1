package hellojpa;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = em.find(Member.class, 160L);
            member.setName("AAA");

            em.clear();

            Member member2 = em.find(Member.class, 160L);

            System.out.println("===============");
            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
