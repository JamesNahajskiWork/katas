import React from "react";
import { useState, useEffect } from "react";

const Mine = ({ mine, rowIndex, columnIndex, pressSquare, alive }) => {
	/**
	 * Is mine and exploded: -2
	 * Is unknown -1
	 * Is known and 0 adjacent 0
	 * Is known and 1 or more adjacent n
	 */
	const [mineStringRep, setmineStringRep] = useState(" ");
	useEffect(() => {
		if (mine === -2) {
			setmineStringRep("*");
		} else if (mine === -1) {
			setmineStringRep(" ");
		} else if (mine === 0) {
			setmineStringRep(" ");
		} else {
			setmineStringRep(mine.toString());
		}
	}, [mine, setmineStringRep]);
	return (
		<td className={mine === -2 ? "dead" : ""}>
			<button
				onClick={() => pressSquare(rowIndex, columnIndex)}
				disabled={mine !== -1 || !alive}
			>
				{mineStringRep}
			</button>
		</td>
	);
};

export default Mine;
