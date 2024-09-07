package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.BoardDTO;
import com.example.QuanLyDuAn.Entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardsByProjectId(Integer projectId);
    Board addBoard(BoardDTO boardDTO);
    Board updateBoard(Integer boardId, BoardDTO boardDTO);
    void deleteBoard(Integer boardId);
}
