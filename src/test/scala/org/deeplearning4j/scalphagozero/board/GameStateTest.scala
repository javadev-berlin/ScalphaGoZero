package org.deeplearning4j.scalphagozero.board

import org.scalatest.FunSpec

class GameStateTest extends FunSpec {

  describe("Starting a new 19x19 game") {
    val start = GameState.newGame(19)
    it("should apply moves") {
      val nextState = start.applyMove(Move.Play(Point(16, 16)))

      assert(start == nextState.previousState.get)
      assert(nextState.board.getPlayer(Point(16, 16)).get == BlackPlayer)
      assert(nextState.nextPlayer == WhitePlayer)
    }
  }
}
