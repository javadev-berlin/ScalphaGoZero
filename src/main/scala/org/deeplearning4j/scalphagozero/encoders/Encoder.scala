package org.deeplearning4j.scalphagozero.encoders

import org.deeplearning4j.scalphagozero.board.{ GameState, Move, Point }
import org.nd4j.linalg.api.ndarray.INDArray

/**
  * Base trait for Go board encoders. An encoder translates game state information
  * into ND4J tensors that can be fed into neural networks.
  *
  * @param boardSize width and height of the board.
  * @param numPlanes number of layers/planes (i.e. depth) to represent colors or empties.
  * @author Max Pumperla
  */
abstract class Encoder(val boardSize: Int = 19, val numPlanes: Int) {

  /**
    * Name of the encoder.
    *
    * @return Encoder name as String
    */
  def name: String

  /**
    * Encode the current game state as board tensor
    *
    * @param gameState GameState instance
    * @return Board tensor representation of the game state
    */
  def encode(gameState: GameState): INDArray

  /**
    * Encodes a given move as board index
    *
    * @param move Go Move instance
    * @return board index of move
    */
  def encodeMove(move: Move): Int

  /**
    * Retrieves a move from a board index
    *
    * @param index board index of a move
    * @return Move instance corresponding to index
    */
  def decodeMoveIndex(index: Int): Move

  /**
    * Turn a board point to an integer index.
    *
    * @param point Board point
    * @return Index representation of the point
    */
  final def pointToIndex(point: Point): Int = boardSize * point.row + point.col

  /**
    * Decodes an index back into a point representation.
    *
    * @param index index of a point
    * @return Board point corresponding to index.
    */
  final def indexToPoint(index: Int): Point = {
    val row = index / boardSize
    val col = index % boardSize
    Point(row + 1, col + 1)
  }

  /**
    * Get number of points on the board.
    *
    * @return Number of board points
    */
  final val numberOfPoints: Int = boardSize * boardSize

  /**
    * Shape of encoded tensors
    *
    * @return Tensor shape as array
    */
  final val shape: Array[Int] = Array(numPlanes, boardSize, boardSize)
}
