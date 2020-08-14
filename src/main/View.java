package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import BoardVO.BoardVO;
import Service.Service;
import Service.ServiceImpl;

public class View {
	Service service = new ServiceImpl();
	private void menu() {
		
		while(true) {
			System.out.println("다음 중 하나를 선택해주세요.");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 게시글 수정");
			System.out.println("3. 게시글 삭제");
			System.out.println("4. 게시글 검색");
			System.out.println("5. 전체 게시글 목록 출력");
			int userInput;
			try {
				Scanner sc = new Scanner(System.in);
				userInput = sc.nextInt();
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("잘못 입력하였습니다. 다시 입력해 주세요..");
				continue; 
			}
			
			switch(userInput) {
			case 1 :
				writeBoard();
				break;
			case 2 :
				modBoard();
				break;
			case 3 : 
				delBoard();
				break;
			case 4 :
				searchBoard();
				break;
			case 5 :
				displayBoardAll();
				break;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력해 주세요..");
				continue;
			}
			break;
		}	// endOf while(true)
	} // endOf menu();

	
	private void delBoard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 게시글의 게시글 번호를 입력해 주세요 : ");
		int userInput = sc.nextInt();
		
		int cnt = service.delBoard(userInput);
		
		if (cnt > 0) {
			System.out.println("삭제되었습니다.");
		}else {
			System.out.println("삭제 실패..");
		}
		menu();
	}

	
	private void modBoard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 글의 작성자를 입력해 주세요 :");
		String boardWriter = sc.nextLine();
		
		System.out.println("게시글의 제목을 입력해주세요 : ");
		String boardTitle = sc.nextLine();
		
		System.out.println("수정 날짜를 입력해 주세요(예 : 20200701) : ");
		String boardDate = sc.next();
		sc.nextLine();	// 버퍼 지우기
		
		System.out.println("내용을 입력해 주세요 :");
		String boardContent = sc.nextLine();
		
		BoardVO bo = new BoardVO(boardTitle, boardWriter, boardDate, boardContent);
		
		int cnt = service.modBoard(bo);
		
		if (cnt > 0) {
			System.out.println("게시글 수정 완료.");
		}else {
			System.out.println("게시글 수정 실패. 입력한 작성자가 작성한 글이 없습니다.");
		}
		menu();
	}


	private void displayBoardAll() {
		System.out.println("=====================");
		System.out.println("번호\t제목\t\t작성자\t작성일\t\t게시글내용");
		System.out.println("=====================");
		
		List<BoardVO> boardList = new ArrayList<>();
		boardList = service.displayBoardAll();
		
		for ( int i = 0 ; i < boardList.size() ; i++) {
			System.out.print(boardList.get(i).getBoardNo() + "\t");
			System.out.print(boardList.get(i).getBoardTitle()+ "\t\t");
			System.out.print(boardList.get(i).getBoardWriter()+ "\t");
			System.out.print(boardList.get(i).getBoardDate()+ "\t\t");
			System.out.print(boardList.get(i).getBoardContent()+ "\t");
			System.out.println();
			System.out.println("----------------------------");
			
		}
		System.out.println("모든 게시글을 출력하였습니다..");
		menu();
	}


	private void searchBoard() {
		System.out.println("=====================");
		System.out.println("게시글 검색을 시작합니다...");
		System.out.println("=====================");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 게시글의 번호를 입력해 주세요 :");
		int boardNo = sc.nextInt();
		
		System.out.println("검색할 게시글의 제목을 입력해주세요(부분 포함 가능) : ");
		String boardTitle = sc.next();
		
		System.out.println("검색할 게시글의 작성자를 입력해 주세요(부분 포함 가능) : ");
		String boardWriter = sc.next();

		System.out.println("검색할 게시글ㄴ의 작성 날짜를 입력해 주세요(예 : 20200701)(부분 포함 가능) : ");
		String boardDate = sc.next();
		sc.nextLine();	// 버퍼 지우기
		
		System.out.println("검색할 게시글의 내용을 입력해 주세요 (부분 포함 가능)");
		String boardContent = sc.next();
		
		BoardVO bo = new BoardVO(boardTitle, boardWriter, boardDate, boardContent);
		bo.setBoardNo(boardNo);
		
		List<BoardVO> boardList = new ArrayList<>();
		
		boardList = service.searchBoard(bo);
		
		System.out.println("=====================");
		System.out.println("번호\t제목\t\t작성자\t작성일\t\t게시글내용");
		System.out.println("=====================");
		
		if (boardList.size() == 0) {
			System.out.println("해당하는 게시글이 존재하지 않습니다.");
		}else {
			for ( int i = 0 ; i < boardList.size() ; i++) {
				System.out.print(boardList.get(i).getBoardNo() + "\t");
				System.out.print(boardList.get(i).getBoardTitle()+ "\t\t");
				System.out.print(boardList.get(i).getBoardWriter()+ "\t");
				System.out.print(boardList.get(i).getBoardDate()+ "\t\t");
				System.out.print(boardList.get(i).getBoardContent()+ "\t");
				System.out.println();
				System.out.println("----------------------------");
			}
		}
		System.out.println("게시글 검색을 완료하였습니다..");
		menu();
	}


	private void writeBoard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("게시글의 제목을 입력해주세요 : ");
		String boardTitle = sc.nextLine();
		
		System.out.println("작성자를 입력해 주세요 : ");
		String boardWriter = sc.nextLine();
		
		System.out.println("작성 날짜를 입력해 주세요(예 : 20200701) : ");
		String boardDate = sc.next();
		sc.nextLine();	// 버퍼 지우기
		
		System.out.println("내용을 입력해 주세요 :");
		String boardContent = sc.nextLine();
		
		BoardVO bo = new BoardVO(boardTitle, boardWriter, boardDate, boardContent);
		
		int cnt = service.writeBoard(bo);
		
		if (cnt > 0) {
			System.out.println("게시글 작성 완료.");
		}else {
			System.out.println("게시글 작성 실패");
		}
		menu();
	}
	
	public static void main(String[] args) {
		View obj = new View();
		obj.menu();
	}
}	// endOf View "Class"
