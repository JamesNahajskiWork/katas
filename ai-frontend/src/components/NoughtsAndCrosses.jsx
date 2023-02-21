import { useReducer, useEffect, React } from "react";
import Cell from "./Cell";
import axios from "axios";

const NoughtsAndCrosses = () => {
	const getInitialBoardState = () => {
		axios
			.get("http://localhost:8080/initial-state")
			.then((res) =>
				dispatch({ type: "fullUpdate", newState: res.data.state })
			);
	};

	useEffect(() => {
		getInitialBoardState();
	}, []);

	const updateBoardState = (prevState, params) => {
		switch (params.type) {
			case "fullUpdate":
				return params.newState;
			default:
				console.log("Undefined type: " + params.type);
				return prevState;
		}
	};

	const [boardState, dispatch] = useReducer(updateBoardState, []);

	const playMove = ({ x, y }) => {
		console.log("X: " + x + ", Y: " + y);
		axios
			.post("http://localhost:8080/make-move", {
				boardState: { state: boardState },
				proposedMove: {
					newValue: "X",
					newPosition: { column: x, row: y },
				},
			})
			.then((res) =>
				dispatch({ type: "fullUpdate", newState: res.data.state })
			);
	};
	return (
		<table>
			{boardState
				? boardState.map((row, rowIndex) => {
						return (
							<tr>
								{row.map((cell, columnIndex) => {
									return (
										<td>
											<Cell
												cellValue={cell}
												playMove={() =>
													playMove({
														type: "singleValueUpdate",
														x: columnIndex,
														y: rowIndex,
													})
												}
											/>
										</td>
									);
								})}
							</tr>
						);
				  })
				: null}
		</table>
	);
};

export default NoughtsAndCrosses;
