import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Airport {

  private final List<? extends Plane> planes;

  public Airport(List<? extends Plane> planes) {
    this.planes = planes;
  }

  @SuppressWarnings("unchecked")
  public List<Plane> getPlanes() {
    return (List<Plane>) planes;
  }

  public List<PassengerPlane> getPassengerPlanes() {
    return planes.stream()
        .filter(PassengerPlane.class::isInstance)
        .map(PassengerPlane.class::cast)
        .collect(Collectors.toList());
  }

  public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
    List<PassengerPlane> passengerPlanes = getPassengerPlanes();
    passengerPlanes.sort(Comparator.comparingInt(PassengerPlane::getPassengersCapacity).reversed());
    return passengerPlanes.get(0);
  }

  public List<MilitaryPlane> getMilitaryPlanes() {
    return planes.stream()
        .filter(MilitaryPlane.class::isInstance)
        .map(MilitaryPlane.class::cast)
        .collect(Collectors.toList());
  }

  public List<MilitaryPlane> getTransportMilitaryPlanes() {
    return getMilitaryPlanes().stream()
        .filter(plane -> plane.getMilitaryType() == MilitaryType.TRANSPORT)
        .collect(Collectors.toList());
  }

  public List<MilitaryPlane> getBomberMilitaryPlanes() {
    return getMilitaryPlanes().stream()
        .filter(plane -> plane.getMilitaryType() == MilitaryType.BOMBER)
        .collect(Collectors.toList());
  }

  public List<ExperimentalPlane> getExperimentalPlanes() {
    return planes.stream()
        .filter(ExperimentalPlane.class::isInstance)
        .map(ExperimentalPlane.class::cast)
        .collect(Collectors.toList());
  }

  public Airport sortByMaxFlightDistance() {
    planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    return this;
  }

  public Airport sortByMaxSpeed() {
    planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    return this;
  }

  public Airport sortByMaxLoadCapacity() {
    planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    return this;
  }

  @Override
  public String toString() {
    return "Airport{" + "planes=" + planes + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Airport)) return false;
    Airport airport = (Airport) o;
    return Objects.equals(planes, airport.planes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(planes);
  }
}
