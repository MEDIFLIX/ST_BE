package com.dashboardbe.api.service;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.BoardResponseDTO;
import com.dashboardbe.api.repository.BoardRepository;
import com.dashboardbe.domain.Admin;
import com.dashboardbe.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 작성 서비스 로직
     */
    @Override
//    @LoginCheck
    public void save(String content, Admin admin) {
        Board board = Board.builder()
                        .admin(admin)
                        .content(content)
                        .build();
        boardRepository.save(board);
    }

    /**
     * 게시물 리스트 서비스 로직
     */
    @Override
//    @LoginCheck
    public List<BoardResponseDTO> list() {
        // 최신 게시물 순으로 sort
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        List<BoardResponseDTO> list = new ArrayList<>();

        for (Board board : boards) {
            BoardResponseDTO boardResponseDTO = BoardResponseDTO.builder()
                    .id(board.getId())
                    .name(board.getAdmin().getName())
                    .content(board.getContent())
                    .createDate(board.getCreateDate().toLocalDate())
                    .build();
            list.add(boardResponseDTO);
        }
        return list;
    }
}
