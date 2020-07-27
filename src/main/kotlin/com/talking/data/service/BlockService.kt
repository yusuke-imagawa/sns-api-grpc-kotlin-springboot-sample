package com.talking.data.service

import com.talking.data.entity.Block
import com.talking.data.repository.BlockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BlockService {

    @Autowired
    private lateinit var blockRepository: BlockRepository

    fun save(block: Block) : Block {
        return blockRepository.save(block)
    }

    fun findByFromUserId(fromId: Long) : List<Block> {
        return blockRepository.findByFromUser_Id(fromId)
    }
}