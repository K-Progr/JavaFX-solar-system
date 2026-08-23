# JavaFX-solar-system

**Java 8 · JavaFX · NetBeans / Ant**

An interactive solar system simulation. Place planets on their orbits, set them
animating counterclockwise around the Sun, and browse their physical data.

## Features

- Sun with glow over a generated starfield, and eight dashed elliptical orbits
- Image buttons along the bottom place each planet on its orbit in grey, with
  its own sound effect
- Clicking a name in the left-hand list fills that planet with its texture — or
  raises an error alert, using the planet's own image as the icon, if it hasn't
  been placed yet
- Endless counterclockwise `PathTransition` per planet, each at its own speed
  (Mercury laps in 6 seconds, Neptune in 52)
- The Moon orbits Earth while Earth orbits the Sun; Saturn's ring travels with it
- Right-click for a cascaded "Control Animation" menu — start all / stop all
- A details dialog with a table of mass, distance and orbital speed

## Running it

Built on **JDK 8**, where JavaFX ships with the JDK — no separate SDK or module
path needed. Open in NetBeans and hit Run, or `ant run` from the project root.

## How some of it works

**Images on circles.** Planets are `Circle` shapes filled with an `ImagePattern`
rather than `ImageView` nodes — the shape clips the image to a disc for free, and
switching from grey to textured is one `setFill` call.

**Counterclockwise.** An `Ellipse` used as a path is traversed clockwise on
screen; `setRate(-1)` reverses it without needing a custom `Path`.

**The Moon.** `PathTransition` moves a node via its translate properties, never
its centre. So the Moon and its ring sit in a `Group` whose translates are
*bound* to Earth's — the group inherits Earth's motion, and the Moon's own
transition only handles the small local ellipse. Two bindings, no trigonometry.

**Pause, not stop.** `Animation.stop()` resets the play head, snapping every
planet back to the same point at once. `pause()` freezes each where it is.

**Sound.** All eight `MediaPlayer` objects are built at startup, not per click —
constructing one initialises a native decoder on the FX thread and dropped
frames mid-animation.

## Design notes

The eight planets are parallel local variables — `mercury`, `mercuryImg`,
`mercuryPlaced`, `pt1` — rather than objects. That's why the list handler needs
an eight-branch switch and why most setup appears eight times with only names
changed. A `Planet` class holding the circle, image, transition and flag, with a
`Planet[]`, would collapse it into loops.

It also produced two bugs the compiler couldn't catch: one planet loading
another's image, and seven animations configuring the wrong transition object.
Both copy-paste-and-rename failures — the failure mode that having eight of
everything invites.

## Assets

Planet textures and sound effects were generated procedurally for this project.

