package main

import (
	"fmt"
	"io"
	"net/http"
)

func putHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPut {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}

	// 1. Read the request body using io.ReadAll
	body, err := io.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	defer r.Body.Close()

	// 2. Print the body as a string to the console
	fmt.Printf("Received PUT request:\n%s\n", string(body))

	// 3. Send a response back to the client
	w.WriteHeader(http.StatusOK)
	//w.Write([]byte("Payload received successfully"))
	w.Write([]byte("Server received: " + string(body)))
}

func main() {
	http.HandleFunc("/resource/", putHandler)
	fmt.Println("Server starting on :8080...")
	if err := http.ListenAndServe(":8080", nil); err != nil {
		fmt.Printf("Server failed to start: %v\n", err)
	}
}
