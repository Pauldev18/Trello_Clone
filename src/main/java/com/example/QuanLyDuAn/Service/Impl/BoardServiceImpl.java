package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.BoardDTO;
import com.example.QuanLyDuAn.Entity.Board;
import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Repository.BoardRepository;
import com.example.QuanLyDuAn.Repository.ProjectRepository;
import com.example.QuanLyDuAn.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Board> getBoardsByProjectId(Integer projectId) {
        return boardRepository.findByProjectProjectId(projectId);
    }

    @Override
    public Board addBoard(BoardDTO boardDTO) {

        Project project = projectRepository.findById(boardDTO.getProjectId()).orElseThrow(() ->
                new IllegalArgumentException("Invalid project ID"));

        Board board = new Board();
        board.setBoardName(boardDTO.getBoardName());
        board.setDescription(boardDTO.getDescription());
        board.setProject(project);

        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Integer boardId, BoardDTO boardDTO) {
        Optional<Board> existingBoard = boardRepository.findById(boardId);
        if (existingBoard.isPresent()) {
            Board board = existingBoard.get();
            Project project = projectRepository.findById(boardDTO.getProjectId()).orElseThrow(() ->
                    new IllegalArgumentException("Invalid project ID"));

            board.setBoardName(boardDTO.getBoardName());
            board.setDescription(boardDTO.getDescription());
            board.setProject(project);

            return boardRepository.save(board);
        } else {
            throw new RuntimeException("Board not found with id: " + boardId);
        }
    }

    @Override
    public void deleteBoard(Integer boardId) {
        boardRepository.deleteById(boardId);
    }
}
