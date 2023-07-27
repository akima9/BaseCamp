//package com.giyong.community;
//
//import com.giyong.community.entity.Member;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class Main {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("basecamp");
//        EntityManager entityManager = emf.createEntityManager();
//        EntityTransaction tx = entityManager.getTransaction();
//
//        tx.begin();
//
//        try {
//            Member member = new Member("akima9", "1234");
//            entityManager.persist(member);
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            entityManager.close();
//        }
//    }
//}
