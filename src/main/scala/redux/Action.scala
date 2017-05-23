package redux

trait Action {
  val actionType: String
}

case class BasicAction(actionType: String) extends Action
