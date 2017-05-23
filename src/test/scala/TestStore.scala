import org.scalatest.FunSuite
import redux.{Action, BasicAction, BasicStore}

class TestStore extends FunSuite {

  case class Task(name: String, description: String)

  type TodoList = List[Task]
  val initialState = List(Task("Foo", "Task foo"), Task("Bar", "Task bar"))
  val todoListReducer = (todoList: TodoList, action: Action) => action match {
    case add if action.actionType == "ADD" => Task("Foo1", "Task foo1") :: todoList
    case _ => initialState
  }
  test("BasicStore constructor should create a TodoList Store with initial size of 2.") {
    val store = new BasicStore[TodoList](initialState, todoListReducer)
    assert(store.getState().size == 2)
  }

  test("Dispatch ADD action should create a new task.") {
    val store = new BasicStore[TodoList](initialState, todoListReducer)
    store.dispatch(new BasicAction("ADD"))
    assert(store.getState().size == 3)
  }
}
