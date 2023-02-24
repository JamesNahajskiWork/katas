import { Toaster } from "react-hot-toast";
import "./App.css";
import NoughtsAndCrosses from "./components/NoughtsAndCrosses";

function App() {
	return (
		<div className="App">
			<Toaster />
			<NoughtsAndCrosses />
		</div>
	);
}

export default App;
