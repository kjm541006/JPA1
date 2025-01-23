package hellojpa;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            em.persist(member);

            Member findMember = em.find(Member.class, 100L);
            // 커밋 전 조회 가능
            System.out.println("findMember.Id = " + findMember.getId());
            System.out.println("findMember.Name = " + findMember.getName());
            
            Member findMember2 = em.find(Member.class, 100L);
            // 영속 엔티티의 동일성 보장
            boolean checkSameEntity = findMember == findMember2;
            System.out.println("checkSameEntity = " + checkSameEntity);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
