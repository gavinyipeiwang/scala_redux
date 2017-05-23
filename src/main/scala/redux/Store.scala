package redux


trait Store[T] {
  def getState(): T

  def subscribe(f: () => Unit): Unit

  def dispatch(action: Action): Unit
}

class BasicStore[T](initialState: T, reducer: (T, Action) => T) extends Store[T] {
  var state = initialState
  var listeners = List.empty[() => Unit]

  def getState(): T = state

  def subscribe(listener: () => Unit): Unit = {
    listeners = listener :: listeners
  }

  def dispatch(action: Action): Unit = {
    //change state
    state = reducer(state, action)
    listeners.foreach(listener => listener())
  }
}
