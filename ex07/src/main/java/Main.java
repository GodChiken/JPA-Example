import multiKeyNonIdentifying.idClass.Parent;
import multiKeyNonIdentifying.idClass.ParentId;
import oneToOneIdentifying.Board;
import oneToOneIdentifying.BoardDetail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Parent parent = new Parent();
        parent.setId1("myId1");
        parent.setId2("myId2");
        parent.setName("parentName");
        em.persist(parent);

        ParentId parentId = new ParentId("myId1", "myId2");
        parent = em.find(Parent.class, parentId);

        System.out.println("parent find : " + parent);


        multiKeyNonIdentifying.embeddedId.ParentId parentId1 = new multiKeyNonIdentifying.embeddedId.ParentId("123","321");
        multiKeyNonIdentifying.embeddedId.ParentId parentId2 = new multiKeyNonIdentifying.embeddedId.ParentId("123","321");

        System.out.println("equal?? =? " + parentId1.equals(parentId2));


        Board board = new Board();
        board.setTitle("김동훈 책");

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setContent("김동훈 책 내용");
        boardDetail.setBoard(board);

        board.setBoardDetail(boardDetail);

        em.persist(boardDetail);
        em.flush();

        Board board2 = em.find(Board.class, 1L);
        System.out.println(board2.getTitle());
        System.out.println(board2.getBoardDetail().getContent());


        tx.commit();
        em.close();
        emf.close();
    }
}
