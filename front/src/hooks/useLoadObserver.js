import {useEffect, useRef} from "react";

export default function useLoadObserver(isLoading, indicatorRef, finished, callback) {
    const indicatorObserver = useRef();
    useEffect(() => {
        if (!indicatorRef.current) return;
        if (indicatorObserver.current) indicatorObserver.current.disconnect();
        if (finished) return;
        const cb = function (entries) {
            if (entries[0].isIntersecting) {
                callback();
            }
        };
        indicatorObserver.current = new IntersectionObserver(cb);
        indicatorObserver.current.observe(indicatorRef.current);
    }, [isLoading]);
}