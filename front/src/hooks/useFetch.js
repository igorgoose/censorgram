import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function useFetch(callback) {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetching = async (...args) => {
        try {
            setLoading(true);
            await callback(args);
        } catch (e) {
            setError(e);
        } finally {
            setLoading(false);
        }
    }

    return [fetching, loading, error];
}