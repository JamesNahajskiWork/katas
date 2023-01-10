import "./App.css";
import { useState, useEffect } from "react";
import Cell from "./Cell";

function App() {
	const BOARD_SIZE = 15;
	const createEmptyBoard = () => {
		let emptyBoard = [];
		for (let i = 0; i < BOARD_SIZE; i++) {
			let row = [];
			for (let j = 0; j < BOARD_SIZE; j++) {
				row.push(0);
			}
			emptyBoard.push(row);
		}
		return emptyBoard;
	};
	const [board, setBoard] = useState(createEmptyBoard());
	const [running, setRunning] = useState(false);
	const updateBoard = () => {
		let newBoard = [];
		for (let y = 0; y < board.length; y++) {
			let row = board[y];
			let newRow = [];
			for (let x = 0; x < row.length; x++) {
				let cell = row[x];
				newRow.push(isAlive(y, x, cell));
			}
			newBoard.push(newRow);
		}
		setBoard(newBoard);
	};
	const isAlive = (y, x, alive) => {
		let neighboursCount = countAdjacent(y, x);
		if (alive && (neighboursCount === 2 || neighboursCount === 3)) {
			return 1;
		} else if (alive) {
			return 0;
		} else if (neighboursCount === 3) {
			return 1;
		} else {
			return 0;
		}
	};
	const adjacentCoords = [
		[-1, -1],
		[-1, 0],
		[-1, 1],
		[0, 1],
		[1, 1],
		[1, 0],
		[1, -1],
		[0, -1],
	];
	const countAdjacent = (y, x) => {
		let adjacentCount = 0;
		adjacentCoords.forEach((coords) => {
			let newX = x + coords[1];
			let newY = y + coords[0];
			if (
				!(
					newX < 0 ||
					newY < 0 ||
					newX >= board[0].length ||
					newY >= board.length
				)
			) {
				if (board[newY][newX] === 1) {
					adjacentCount++;
				}
			}
		});
		return adjacentCount;
	};
	const updateCell = (y, x, newState) => {
		let newBoard = [...board];
		newBoard[y][x] = newState;
		setBoard(newBoard);
	};
	const clearBoard = () => {
		setBoard(createEmptyBoard());
	};

	// useEffect(() => {
	// 	if (running) {
	// 		console.log("ASD");
	// 		const runningLoop = setInterval(() => updateBoard(), 1000);
	// 		if (!running) {
	// 			clearInterval(runningLoop);
	// 		}
	// 	}
	// });

	return (
		<>
			<table>
				<tbody>
					{board.map((row, rowKey) => {
						return (
							<tr>
								{row.map((cell, cellKey) => {
									return (
										<td>
											<Cell
												cell={cell}
												rowId={rowKey}
												cellId={cellKey}
												updateCell={updateCell}
												key={
													rowKey.toString +
													cellKey.toString
												}
											/>
										</td>
									);
								})}
							</tr>
						);
					})}
				</tbody>
			</table>
			<button onClick={() => updateBoard()}>Update board</button>
			<button onClick={() => clearBoard()}>Clear board</button>
			<button onClick={() => setRunning(true)}>Go</button>
			<button onClick={() => setRunning(false)}>Stop</button>
		</>
	);
}

export default App;
