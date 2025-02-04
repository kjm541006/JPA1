package hellojpa;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            // 1차 캐시에 없으면 db에서 조회함
            Member member = em.find(Member.class, 160L);
            member.setName("AAA");

            System.out.println("===============");

            // select문 실행 x , 영속성 컨텍스트에서 조회
            Member member2 = em.find(Member.class, 160L);
            member2.setName("BBB");

            System.out.println("===============");

            // 영속성 컨텍스트 완전 초기화
            em.clear();

            // select문 실행, db에서 조회
            Member member3 = em.find(Member.class, 160L);

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
