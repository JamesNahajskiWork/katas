import "./App.css";
import Mine from "./Mine";
import React, { useState, useEffect } from "react";
import axios from "axios";

// Sample response:
// {
//   mines: [
// 		[1, 0, 0, 0, 0, 0],
// 		[1, 0, 0, 0, 0, 0],
// 		[1, 0, 0, 0, 0, 0],
// 		[0, 0, 0, 0, 1, 0],
// 		[0, 0, 0, 0, 0, 0],
// 	]
//   alive: true
// }

function App() {
	const [alive, setAlive] = useState(true);
	const [mines, setMines] = useState([]);
	const [refresh, setrefresh] = useState(0);
	const resetBoard = () => {
		axios.get("http://localhost:8080/initial-state").then((res) => {
			setAlive(true);
			setMines(res.data);
			setrefresh(refresh + 1);
		});
	};
	useEffect(() => {
		resetBoard();
	}, [setMines]);

	const pressSquare = (row, column) => {
		axios
			.post("http://localhost:8080/sweep", {
				x: column,
				y: row,
			})
			.then((res) => {
				setMines(res.data);
				setAlive(
					res.data.reduce((aliveCount, row) => {
						return (
							aliveCount +
							row.reduce((rowAliveCount, cell) => {
								return rowAliveCount + (cell === -2 ? 1 : 0);
							}, 0)
						);
					}, 0) === 0
				);
			})
			.catch((err) => {
				console.log(err);
			});
	};
	return (
		<div className="App">
			<table>
				<tbody>
					{mines
						? mines.map((row, rowIndex) => (
								<tr key={rowIndex}>
									{row.map((mine, columnIndex) => (
										<Mine
											key={rowIndex + "" + columnIndex}
											mine={mine}
											rowIndex={rowIndex}
											columnIndex={columnIndex}
											pressSquare={pressSquare}
											alive={alive}
											refresh={refresh}
										/>
									))}
								</tr>
						  ))
						: null}
				</tbody>
			</table>
			<button
				onClick={() => {
					resetBoard();
				}}
			>
				Reset
			</button>
		</div>
	);
}

export default App;
