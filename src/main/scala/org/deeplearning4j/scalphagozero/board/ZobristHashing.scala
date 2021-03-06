package org.deeplearning4j.scalphagozero.board

import scala.util.Random

/**
  * Zobrist hashing for board positions
  *
  * @author Max Pumperla
  */
object ZobristHashing {

  private final val SEED = 1
  private final val RAND = new Random(SEED)
  private final val MAX_BOARD_SIZE = 19

  final val ZOBRIST: Map[(Point, Option[Player]), Long] = {
    for (i <- 1 to MAX_BOARD_SIZE;
         j <- 1 to MAX_BOARD_SIZE;
         occ <- Seq(None, Some(BlackPlayer), Some(WhitePlayer))) yield (Point(i, j), occ)
  }.map(p => p -> RAND.nextLong()).toMap

}
