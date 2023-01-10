import React from "react";

const Cell = ({ cell, rowId, cellId, updateCell }) => {
	let cellClass = cell ? "cell alive" : "cell dead";
	const cellClicked = () => {
		let newCellValue = cell ? 0 : 1;
		updateCell(rowId, cellId, newCellValue);
	};
	return (
		<button onClick={() => cellClicked()}>
			<div className={cellClass}></div>
		</button>
	);
};

export default Cell;
