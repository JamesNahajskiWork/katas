import React from "react";

const Cell = ({
	cellValue,
	playMove,
	rowIndex,
	columnIndex,
	disabledButton,
}) => {
	console.log(disabledButton);
	return (
		<button onClick={() => playMove()} disabled={disabledButton}>
			{cellValue ? cellValue : " "}
		</button>
	);
};

export default Cell;
