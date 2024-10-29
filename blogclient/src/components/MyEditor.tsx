import { CKEditor } from '@ckeditor/ckeditor5-react';
import { editorConfig } from '../config/editorConfig';
import { ClassicEditor } from 'ckeditor5';

import 'ckeditor5/ckeditor5.css';
import { useState } from 'react';

export default function MyEditor() {
	const [text, setText] = useState("");
	return (
		<>
		<CKEditor
            editor={ClassicEditor}
            config={editorConfig}
			onChange={(event, editor) => {
				const data = editor.getData();
				setText(data);
			}}
        />
		<div>
			{text}
		</div>
		</>
	);
}
