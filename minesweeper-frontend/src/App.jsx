import "./App.css";
import Mine from "./Mine";
import React, { useState } from "react";
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
	const [mines, setMines] = useState([
		[1, 0, 0, 0, 0, 0],
		[1, 0, 0, 0, 0, 0],
		[1, 0, 0, 0, 0, 0],
		[0, 0, 0, 0, 1, 0],
		[0, 0, 0, 0, 0, 0],
	]);
	const [alive, setAlive] = useState(true);
	const pressSquare = (row, column) => {
		axios
			.post("http://localhost:8080/sweep", {
				x: row,
				y: column,
			})
			.then((res) => {
				setMines(res.mines);
				setAlive(res.alive);
			})
			.catch((err) => {
				console.log(err);
			});
	};
	return (
		<div className="App">
			<table>
				<tbody>
					{mines.map((row, rowIndex) => (
						<tr>
							{row.map((mine, columnIndex) => (
								<Mine
									mine={mine}
									rowIndex={rowIndex}
									columnIndex={columnIndex}
									pressSquare={pressSquare}
								/>
							))}
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
}

export default App;
