package lab1;

import java.util.*;

class BasicOperation {
	public Boolean allSamePoints(Point p1, Point p2, Point p3, Point point4) {
		Optional<Point> p4 = Optional.ofNullable(point4);
		if (p4.isPresent()) {
			if (p1.x == p2.x && p1.x == p3.x && p1.x == p4.get().x && p1.y == p2.y && p1.y == p3.y
					&& p1.y == p4.get().y) {
				return true;
			} else {
				return false;
			}
		} else {
			if (p1.x == p2.x && p1.x == p3.x && p1.y == p2.y && p1.y == p3.y) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Boolean isCollinear(Point p1, Point p2, Point p3) {
		int result = p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isInBetween(Point p1, Point p2, Point qp) {
		if (p1.x < p2.x) {
			if (qp.x > p1.x && qp.x < p2.x) {
				return true;
			} else {
				return false;
			}
		} else if (p1.x > p2.x) {
			if (qp.x < p1.x && qp.x > p2.x) {
				return true;
			} else {
				return false;
			}
		} else {
			// when it is parallel to y-axis;
			if (p1.y < p2.y) {
				if (qp.y > p1.y && qp.y < p2.y) {
					return true;
				} else {
					return false;
				}
			} else {
				if (qp.y < p1.y && qp.y > p2.y) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}

class Point {
	int x, y;

	public Point() {
		int[] points = getPointCoordinates();
		x = points[0];
		y = points[1];
	}

	public int[] getPointCoordinates() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter x coordinate: ");
		int x_coordinate = sc.nextInt();
		System.out.print("Enter y coordinate: ");
		int y_coordinate = sc.nextInt();
		int pointCoordinates[] = { x_coordinate, y_coordinate };
		return pointCoordinates;
	}

	public void printPointCoordinate() {
		System.out.println("(" + x + "," + y + ")");
	}
}

class LineSegment {
	Point point1, point2;

	public LineSegment() {
		System.out.println("Provide coordinate for first point of the line segment => ");
		point1 = new Point();
		System.out.println("Provide coordinate for second point of the line segment => ");
		point2 = new Point();
	}

	public void printLineSegment() {
		System.out.println("(" + point1.x + "," + point1.y + "), (" + point2.x + "," + point2.y + ")");
	}
}

class Ray extends BasicOperation {
	Point point1, point2, infPoint;

	public Ray() {
		System.out.println("Provide coordinate for first point of the Ray => ");
		point1 = new Point();
		System.out.println("Provide coordinate for second point of Ray => ");
		point2 = new Point();
		System.out.println("Provide coordinate for infinity point of Ray => ");
		infPoint = new Point();
	}

	public void printRay() {
		System.out.println("(" + point1.x + "," + point1.y + "), (" + point2.x + "," + point2.y + "), (" + infPoint.x
				+ "," + infPoint.y + ")");
	}

	public Boolean isValid() {
		Boolean isCollinear = this.isCollinear(point1, point2, infPoint);
		Boolean isInBetween = this.isInBetween(point1, point2, infPoint);
		Boolean isSamePoints = this.allSamePoints(point1, point2, infPoint, null);
		if (isCollinear == true && isInBetween == false && isSamePoints == false) {
			return true;
		} else {
			return false;
		}
	}
}

class Line extends BasicOperation {
	Point point1, point2, infPoint1, infPoint2;

	public Line() {
		System.out.println("Provide coordinate for first point of the Line => ");
		point1 = new Point();
		System.out.println("Provide coordinate for second point of Line => ");
		point2 = new Point();
		System.out.println("Provide coordinate for first infinity point of Line => ");
		infPoint1 = new Point();
		System.out.println("Provide coordinate for second infinity point of Line => ");
		infPoint2 = new Point();
	}

	public void printLine() {
		System.out.println("(" + infPoint1.x + "," + infPoint1.y + "), (" + point1.x + "," + point1.y + "), " + "("
				+ point2.x + "," + point2.y + "), (" + infPoint2.x + "," + infPoint2.y + ")");
	}

	public Boolean isValid() {
		Boolean isCollinearInf1 = this.isCollinear(point1, point2, infPoint1);
		Boolean isCollinearInf2 = this.isCollinear(point1, point2, infPoint2);
		Boolean isInBetweenInf1 = this.isInBetween(point1, point2, infPoint1);
		Boolean isInBetweenInf2 = this.isInBetween(point1, point2, infPoint2);
		Boolean isSamePoints = this.allSamePoints(point1, point2, infPoint1, infPoint2);

		if (isCollinearInf1 == true && isCollinearInf2 == true && isInBetweenInf1 == false && isInBetweenInf2 == false
				&& isSamePoints == false) {
			if (point1.x < point2.x) {
				if (infPoint1.x < point1.x && infPoint2.x > point2.x) {
					return true;
				} else {
					return false;
				}
			} else {
				if (infPoint1.x > point1.x && infPoint2.x < point2.x) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}

public class GeometricObject {
	public static void main(String args[]) {
		System.out.println("Getting point input => ");
		Point point1 = new Point();
		System.out.println("---- User input point is ---- ");
		point1.printPointCoordinate();
		System.out.println("******************");

		System.out.println("Getting line segment input => ");
		LineSegment lineSegment1 = new LineSegment();
		System.out.println("---- User input line segment is ---- ");
		lineSegment1.printLineSegment();
		System.out.println("******************");

		System.out.println("Getting ray input => ");
		Ray ray = new Ray();
		if (ray.isValid()) {
			System.out.println("---- User input ray is ---- ");
			ray.printRay();
		} else {
			System.out
					.println("Invalid ray data!!! Either infinity point is not collinear or lies between two points.");
		}
		System.out.println("******************");

		System.out.println("Getting line input => ");
		Line line = new Line();
		if (line.isValid()) {
			System.out.println("---- User input line is ---- ");
			line.printLine();
		} else {
			System.out.println(
					"Invalid line data. Either infinity points are not collinear or invalid or lie between two points.");
		}
		System.out.println("******************");
	}
}
