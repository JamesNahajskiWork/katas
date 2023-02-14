import "./App.css";
import { useReducer, useEffect } from "react";
import Cell from "./Cell";
import axios from "axios";

function App() {
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

	const playMove = ({ type, x, y }) => {
		let newState = [...boardState];
		newState[y][x] = "X";
		axios
			.post("http://localhost:8080/get-move", { state: newState })
			.then((res) =>
				dispatch({ type: "fullUpdate", newState: res.data.state })
			);
	};
	return (
		<div className="App">
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
		</div>
	);
}

export default App;
