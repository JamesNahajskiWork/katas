import { useReducer, useEffect, React, useState } from "react";
import Cell from "./Cell";
import axios from "axios";
import { toast } from "react-hot-toast";

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

	const [gameWinner, setGameWinner] = useState(null);

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
			.then((res) => {
				dispatch({
					type: "fullUpdate",
					newState: res.data.boardState.state,
				});
				setGameWinner(res.data.winner);
				if (res.data.winner !== null) {
					toast.success(res.data.winner + " won");
				}
			});
	};
	console.log(gameWinner);
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
												disabledButton={
													gameWinner !== null
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
